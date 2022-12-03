import {FC} from "react";
import {UserAttempt} from "../../services/attempts";

export interface AttemptRowProps {
    userAttempt: UserAttempt,
    key: string
}

export const AttemptRow: FC<AttemptRowProps> = ({userAttempt}) => {
    const attempt = userAttempt.attempt;

    return (
        <tr>
            <td>{userAttempt.number+1}</td>
            <td>{attempt.coords.x.toFixed(2)}</td>
            <td>{attempt.coords.y.toFixed(2)}</td>
            <td>{attempt.coords.r.toFixed(2)}</td>
            <td>{attempt.shot.message}</td>
            <td>{attempt.shot.execTime} ns</td>
            <td>{new Date(attempt.currTime).toTimeString()}</td>
        </tr>
    )
}