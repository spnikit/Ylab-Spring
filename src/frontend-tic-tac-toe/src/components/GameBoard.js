import AlertBlock from "./AlertBlock";
import {Box, Button} from "@chakra-ui/react";
import PlayerToMove from "./PlayerToMove";
import CellGrid from "./CellGrid";
import Cell from "./Cell";
import ReplayButton from "./ReplayButton";


function GameBoard({startNewGame, makeMove, state, setState, replayState, setReplayState}) {

    const {
        winner,
        draw,
        player1,
        player2,
        idToMove
    } = state;

    const winnerName = winner === 1 ?
        player1 :
        player2;

    const message = winner ? `Game over! Winner is  ${winnerName}`
        : "Game is over. Result is DRAW";

    const status = winner ? "success" : "info";

    const cells = Array.from(Array(9), (_, i) => i + 1);


    if (winner || draw) {
        return (
            <>
                <AlertBlock status={status} message={message}/>
                <Button onClick={startNewGame} colorScheme="teal" mt="1rem">Start New Game</Button>
                <ReplayButton setState={setState}
                              {...replayState}
                              setReplayState={setReplayState}/>
            </>
        )
    }
    if (player1 && player2) {
        return (
            <Box textAlign="center">
                <PlayerToMove id={idToMove}/>
                <CellGrid>
                    {cells.map(cell => <Cell key={cell} id={idToMove} makeMove={() => makeMove(cell)}/>)}
                </CellGrid>
            </Box>
        );
    }
}

export default GameBoard;