import {Alert, AlertIcon, AlertTitle,} from '@chakra-ui/react'


function AlertBlock({message, status}){

    return (
        <Alert status={status} m="2rem">
            <AlertIcon />
            <AlertTitle mr={2}>{message}</AlertTitle>
        </Alert>
    );
}

export default AlertBlock;