import {Button} from "@chakra-ui/react";

function ReplayCell({symbol}){
    return (
        <Button
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

export default ReplayCell;