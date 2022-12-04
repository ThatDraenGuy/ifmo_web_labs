import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";
import {ShooterForm} from "./ShooterForm";
import {Col, Container, Row} from "react-bootstrap";
import {AttemptsController} from "./attempts/AttemptsController";
import {SideMenu} from "./sideMenu/SideMenu";

export const Home: FC<any> = ({}) => {
    return(
        <Container>
            <SideMenu/>
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
                    <AttemptsController/>
                </Col>
            </Row>
        </Container>
    )
}