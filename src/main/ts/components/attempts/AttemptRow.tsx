import {FC} from "react";
import {UserAttemptInfo} from "../../services/attempts";

export interface AttemptRowProps {
    userAttempt: UserAttemptInfo
}

export const AttemptRow: FC<AttemptRowProps> = ({userAttempt}) => {
    const attempt = userAttempt.attemptInfo;

    return (
        <tr>
            <td>{userAttempt.number}</td>
            <td>{attempt.coords.x.toFixed(2)}</td>
            <td>{attempt.coords.y.toFixed(2)}</td>
            <td>{attempt.coords.r.toFixed(2)}</td>
            <td>{attempt.shot.message}</td>
            <td>{attempt.shot.execTime} ns</td>
            <td>{new Date(attempt.currTime).toTimeString()}</td>
        </tr>
    )
}