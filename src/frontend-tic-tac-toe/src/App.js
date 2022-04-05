import './App.css';
import {Container} from '@chakra-ui/react'
import {useState} from "react";
import AlertBlock from "./components/AlertBlock";
import GameBoard from "./components/GameBoard";
import DisplayPlayerInput from "./components/DisplayPlayerInput";
import ReplayGameBoard from "./components/ReplayGameBoard";

function App() {
    const [state, setState] = useState({
        player1: "",
        player2: "",
        idToMove: 1,
        winner: "",
        draw: false,
        message: "",
        status: "game"
    });


    const [replayState, setReplayState] = useState({
        replayMoves: [new Array(9).fill(" ")],
        pointer: 0,
    });
    const {replayMoves, pointer} = replayState;
    const currentReplayMoves = (pointer < replayMoves.length) ?
        replayMoves[pointer] :
        replayMoves[replayMoves.length - 1]


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
            .then(response => {
                return response.json();
            })
            .then(data => {
                setState({
                    ...state,
                    player1: data["_id"] === 1 ? data["_name"] : state.player1,
                    player2: data["_id"] === 2 ? data["_name"] : state.player2,
                    message: data.message,
                    status: "game"
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
                    message: data?.message,
                    status: "game"
                })

                setReplayState({
                    replayMoves: [new Array(9).fill(" ")],
                    pointer: 0,
                })
            }).catch(console.log)


    }

    return (

        <Container centerContent mt="3rem">

            {state.status === "replay" &&
                <ReplayGameBoard cells={currentReplayMoves}/>}

            <DisplayPlayerInput {...state} setPlayerName={setPlayerName}/>

            {state.message && <AlertBlock message={state.message} status="error"/>}

            <GameBoard state={state}
                       makeMove={makeMove}
                       startNewGame={startNewGame}
                       setState={setState}
                       replayState={replayState}
                       setReplayState={setReplayState}
            />

        </Container>

    );

}

export default App;
