import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";
import {ShooterForm} from "./ShooterForm";
import {AttemptsTable} from "./attempts/AttemptsTable";
import {Col, Container, Row} from "react-bootstrap";

export const Home: FC<any> = ({}) => {
    return(
        <Container>
            <Row>
                <Col>
                    <QuadrantsGraph/>
                </Col>
                <Col>
                    <ShooterForm/>
                </Col>
            </Row>
            <Row>
                <Col>
                    <AttemptsTable/>
                </Col>
            </Row>
        </Container>
    )
}