import {Input} from '@chakra-ui/react'
import {Button} from '@chakra-ui/react'
import {Box} from '@chakra-ui/react'
import {useState} from "react";


function PlayerInput({setPlayerName}) {

    const [name, setName] = useState("");

    const handleChange = (event) => {
        setName(event.target.value);

    }

    const handleBtnClick = () => {
        setPlayerName(name)
        setName("");
    }

    return (
        <Box display="flex" mb="2rem">
            <Input value={name} onChange={handleChange} placeholder="Enter Player's name"/>
            <Button onClick={handleBtnClick} display="inline-block" colorScheme='blue' ml="1rem">OK</Button>
        </Box>
    );
}


export default PlayerInput;