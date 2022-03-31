import {Input} from '@chakra-ui/react'
import {Button} from '@chakra-ui/react'
import {Box} from '@chakra-ui/react'
import {useRef, useState} from "react";


function PlayerInput({setPlayerName, number}) {

    const [name, setName] = useState("");
    const inputRef = useRef();

    const handleChange = (event) => {
        setName(event.target.value);

    }

    const handleBtnClick = () => {
        setPlayerName(name)
        setName("");
        inputRef.current.focus();
    }

    return (
        <Box display="flex" mb="2rem">
            <Input ref={inputRef} value={name} onChange={handleChange} placeholder={`Enter Player ${number} name`}/>
            <Button onClick={handleBtnClick} display="inline-block" colorScheme='blue' ml="1rem">OK</Button>
        </Box>
    );
}


export default PlayerInput;