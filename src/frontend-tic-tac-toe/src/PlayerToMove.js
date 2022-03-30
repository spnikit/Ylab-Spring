
import {
    Tag,
    TagLabel
} from '@chakra-ui/react'

function PlayerToMove({id, name}) {

    return (
        <Tag  mb="1rem" variant='outline' colorScheme='blue' size="lg">
            <TagLabel> Player #{id} {name} move</TagLabel>
        </Tag>
    );
}


export default PlayerToMove;