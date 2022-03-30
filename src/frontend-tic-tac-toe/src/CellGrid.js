import React from "react";
import {Grid, GridItem} from '@chakra-ui/react'


function CellGrid({children}) {
    return (
        <Grid templateColumns="repeat(3, 1fr)"
              templateRows="repeat(3, 1fr)"
                maxWidth={300}
        justifyContent="center">
            {React.Children.map(children, child => {
                return (
                    <GridItem>
                        {child}
                    </GridItem>
                );
            })}
        </Grid>
    );
}

export default CellGrid;

