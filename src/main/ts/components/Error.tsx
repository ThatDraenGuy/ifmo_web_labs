import {FC} from "react";
import {Card} from "react-bootstrap";
import {Link} from "react-router-dom";


export const Error: FC<any> = () => {

    return (
        <Card>
            <Card.Body>
                <Card.Title>Error</Card.Title>
                <Card.Subtitle>Page not found</Card.Subtitle>
                <Card.Text>
                    Page you requested wasn't found
                </Card.Text>
                <Link to="/">Back to main page</Link>
            </Card.Body>
        </Card>
    )
}