import {Button} from "@chakra-ui/react";
import {convertStepsArrayToSymbolArray} from "../Utils";
import {useRef} from "react";

function ReplayButton({setReplayState, setState, replayMoves}) {

    const ref = useRef({pointer: 0, movesLength: replayMoves.length});
    ref.current.movesLength = replayMoves.length;

    const handleClick = () => {
        if (replayMoves.length > 2) {
            setState(state => ({...state, status: "replay"}));

            setReplayState(replayState => ({
                ...replayState,
                pointer: 0
            }))
            replayWithInterval(ref, setReplayState);

        } else if (replayMoves.length <= 1) {

            fetch("http://localhost:8080/gameplay/result/last")
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    return Promise.reject(response.statusText);
                })
                .then(data => {
                    const steps = data?.Game?.Step;
                    if (!steps) {
                        return Promise.reject("Steps are empty in last Gameplay response")
                    }
                    return steps;
                })
                .then(steps => {
                    setReplayState(replayState => ({
                        ...replayState,
                        replayMoves: [...replayState.replayMoves,...convertStepsArrayToSymbolArray(steps)]
                    }))
                    setState(state => ({...state, status: "replay"}));
                    replayWithInterval(ref, setReplayState);
                })
                .catch(error => setState(prevState => ({...prevState, message: error.message})))
        }
    }

    return (
        <Button onClick={handleClick} colorScheme="teal" mt="1rem">Replay Last Game</Button>
    );
}


function replayWithInterval(ref, changeState) {

    const id = setInterval(() => {
        if (ref.current.pointer >= ref.current.movesLength) {
            clearInterval(id);
            ref.current.pointer = 0;
            return;
        }
        ref.current.pointer++;
        changeState(replayState => ({
            ...replayState,
            pointer: replayState.pointer + 1
        }));
    }, 1000);

}

export default ReplayButton;