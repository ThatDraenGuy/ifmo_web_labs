import {FC, useEffect} from "react";
import {useAttemptsQuery} from "../../services/attempts";
import {AttemptRow} from "./AttemptRow";
import {Spinner, Table} from "react-bootstrap";


export const AttemptsTable: FC<any> = () => {
    const {data: attempts, isLoading} = useAttemptsQuery();

    if (isLoading) return (<Spinner animation="border"/>)
    return (
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
                    {attempts!=undefined ? attempts.map(attempt => <AttemptRow userAttempt={attempt} key={attempt._links.self.href}/>) : ''}
            </tbody>
        </Table>
    )
}