import './App.css';
import {Container} from '@chakra-ui/react'
import {useState} from "react";
import AlertBlock from "./AlertBlock";
import GameBoard from "./GameBoard";
import DisplayPlayerInput from "./DisplayPlayerInput";

function App() {
    const [state, setState] = useState({
        player1: "",
        player2: "",
        idToMove: 1,
        winner: "",
        draw: false,
        message: ""
    });


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

    return (
        <Container centerContent mt="3rem">

            <DisplayPlayerInput {...state} setPlayerName={setPlayerName} />

            {state.message && <AlertBlock message={state.message} status="error"/>}

            <GameBoard {...state} makeMove={makeMove} startNewGame={startNewGame} />

        </Container>

    );

}

export default App;
