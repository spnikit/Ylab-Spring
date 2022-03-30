import './App.css';
import Cell from "./Cell";
import CellGrid from "./CellGrid";
import {Box, Button, Container} from '@chakra-ui/react'
import {useState} from "react";
import PlayerInput from "./PlayerInput";
import AlertBlock from "./AlertBlock";
import PlayerToMove from "./PlayerToMove";

function App() {
    const [state, setState] = useState({
        player1: "",
        player2: "",
        idToMove: 1,
        winner: "",
        draw: false,
        message: ""
    });

    const cells = Array.from(Array(9), (_, i) => i + 1);

    const makeMove = (cellNumber) => {
        const resource = "http://localhost:8080/gameplay/move";
        const jsonToSend = JSON.stringify({
            cellNumber,
            playerId: state.idToMove
        });

        fetch(resource, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonToSend
        })
            .then(response => response.json())
            .then(data => {
                setState({
                    ...state,
                    idToMove: data?.playerNextMoveId,
                    winner: data?.winnerId,
                    draw: data?.draw,
                    message: data?.message
                })

            })
            .catch(console.log)
    }

    const setPlayerName = (name) => {

        const symbol = state.player1 ? "O" : "X";

        const resource = "http://localhost:8080/gameplay/register/player";
        const jsonToSend = JSON.stringify({
            name,
            symbol
        });

        fetch(resource, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonToSend
        })
            .then(response => response.json())
            .then(data => {
                setState({
                    ...state,
                    player1: data["_id"] === 1 ? data["_name"] : state.player1,
                    player2: data["_id"] === 2 ? data["_name"] : state.player2,
                    message: ""
                })
            })
            .catch(console.log)
    }

    const startNewGame = () => {
        const resource = "http://localhost:8080/gameplay/new";


        fetch(resource)
            .then(res => res.json())
            .then(data => {
                setState({
                    player1: "",
                    player2: "",
                    idToMove: 1,
                    winner: "",
                    draw: false,
                    message: data?.message
                })
            }).catch(console.log)


    }

    const displayPlayerInput = () => {
        if ((!(state.player1 && state.player2))) {
            const playerNumber = state.player1 ? 2 : 1;
            return <PlayerInput setPlayerName={setPlayerName} number={playerNumber}/>;
        }
    }

    const displayBoard = () => {
        const winnerName = state.winner === 1 ?
            state.player1 :
            state.player2;

        const message = state.winner ? `Game over! Winner is  ${winnerName}`
            : "Game is over. Result is DRAW";

        const status = state.winner ? "success" : "info";

        if (state.winner || state.draw) {
            return (
                <>
                    <AlertBlock status={status} message={message}/>
                    <Button onClick={startNewGame} colorScheme="teal" mt="1rem">Start New Game</Button>
                </>
            )
        } else if (state.player1 && state.player2) {
            return (
                <Box textAlign="center">
                    <PlayerToMove id={state.idToMove}/>
                    <CellGrid>
                        {cells.map(cell => <Cell key={cell} id={state.idToMove} makeMove={() => makeMove(cell)}/>)}
                    </CellGrid>
                </Box>
            );
        }
    }


    return (
        <Container centerContent mt="3rem">

            {displayPlayerInput()}

            {state.message && <AlertBlock message={state.message} status="error"/>}

            {displayBoard()}

        </Container>

    );

}

export default App;
