import {FC, useEffect} from "react";
import {useAttemptsQuery} from "../../services/attempts";
import {AttemptRow} from "./AttemptRow";


export const AttemptsTable: FC<any> = () => {
    const {data: attempts, isLoading} = useAttemptsQuery();
    console.log(isLoading)
    return (
        <table>
            <tr>
                <td>Attempt №</td>
                <td>X</td>
                <td>Y</td>
                <td>R</td>
                <td>Result</td>
                <td>Execution time</td>
                <td>Attempt time</td>
            </tr>
            {attempts!=undefined ? attempts.map(attempt => <AttemptRow userAttempt={attempt}/>) : ''}
        </table>
    )
}