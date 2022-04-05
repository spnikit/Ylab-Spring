import PlayerInput from "./PlayerInput";


function DisplayPlayerInput({player1, player2, setPlayerName}) {

    if (player1 && player2) {
        return null;
    }

    const playerNumber = player1 ? 2 : 1;

    return (
        <PlayerInput setPlayerName={setPlayerName} number={playerNumber}/>
    );
}

export default DisplayPlayerInput;