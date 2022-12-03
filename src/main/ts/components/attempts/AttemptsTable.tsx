import {FC, useEffect} from "react";
import {useAttemptsQuery, useClearMutation, UserAttempt} from "../../services/attempts";
import {AttemptRow} from "./AttemptRow";
import {Button, Spinner, Table} from "react-bootstrap";

export interface AttemptsTableProps {
    attempts: Array<UserAttempt>,
    isLoading: boolean
}

export const AttemptsTable: FC<AttemptsTableProps> = ({attempts, isLoading}) => {

    if (isLoading) return (<Spinner animation="border"/>)
    return (
        <div>
            <Table>
                <thead>
                    <tr>
                        <td>Attempt №</td>
                        <td>X</td>
                        <td>Y</td>
                        <td>R</td>
                        <td>Result</td>
                        <td>Execution time</td>
                        <td>Attempt time</td>
                    </tr>
                </thead>
                <tbody>
                        {attempts!=undefined ? attempts.map(attempt => <AttemptRow userAttempt={attempt} key={attempt.number.toString()}/>) : ''}
                </tbody>
            </Table>
        </div>
    )
}