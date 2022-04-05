import {useEffect, useRef} from "react";

export function convertStepsArrayToSymbolArray(steps) {
    const array = new Array(9).fill("");
    return steps.map(step => {
        const symbol = step["_playerId"] === 1 ? "X" : "O";
        const coord = XYCoordToNumpad(step.xCoord, step.yCoord);

        array[coord] = symbol;

        return [...array];

    })
}


export function useInterval(callback, delay) {
    const savedCallback = useRef();

    // Remember the latest callback.
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    // Set up the interval.
    useEffect(() => {
        function tick() {
            savedCallback.current();
        }
        if (delay !== null) {
            let id = setInterval(tick, delay);
            return () => clearInterval(id);
        }
    }, [delay]);
}


function XYCoordToNumpad(pointX, pointY) {
    if (pointX === 0 && pointY === 0) {
        return 0;
    }
    if (pointX === 1 && pointY === 0) {
        return 1;
    }
    if (pointX === 2 && pointY === 0) {
        return 2;
    }
    if (pointX === 0 && pointY === 1) {
        return 3;
    }
    if (pointX === 1 && pointY === 1) {
        return 4;
    }
    if (pointX === 2 && pointY === 1) {
        return 5;
    }
    if (pointX === 0 && pointY === 2) {
        return 6;
    }
    if (pointX === 1 && pointY === 2) {
        return 7;
    }
    if (pointX === 2 && pointY === 2) {
        return 8;
    } else {
        throw new Error("Illegal argument")
    }
}