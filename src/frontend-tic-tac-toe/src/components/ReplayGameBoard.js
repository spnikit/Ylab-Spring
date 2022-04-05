import CellGrid from "./CellGrid";
import {Box} from "@chakra-ui/react";
import ReplayCell from "./ReplayCell";

function ReplayGameBoard({cells}) {

    if (!cells || cells.length <= 0) {
        return null;
    }

    return (
        <Box textAlign="center">
            <CellGrid>
                {cells.map((cell, id) => <ReplayCell key={id} symbol={cell} />)}
            </CellGrid>
        </Box>
    );
}


export default ReplayGameBoard;