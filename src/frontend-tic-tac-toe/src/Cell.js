import {Button} from '@chakra-ui/react'
import {useState} from "react";

function Cell({id, makeMove}) {
    const [isClicked, setIsClicked] = useState(false);
    const [symbol, setSymbol] = useState("");

    const symbolToSet = id === 1 ? "X" : "O";


    const handleOnClick = () => {
        if (isClicked) {
            return;
        }
        makeMove();
        setSymbol(symbolToSet);
        setIsClicked(true);
    }

    return (
        <Button
            onClick={handleOnClick}
            colorScheme='teal'
            variant="outline"
            d="inline-block"
            borderRadius="1px"
            w="7rem"
            h="7rem"
            fontSize="5rem"
        >{symbol}</Button>
    );
}

export default Cell;