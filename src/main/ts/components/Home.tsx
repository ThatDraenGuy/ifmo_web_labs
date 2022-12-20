import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";
import {ShooterForm} from "./ShooterForm";
import {Col, Container, Row} from "react-bootstrap";
import {AttemptsController} from "./attempts/AttemptsController";
import {SideMenu} from "./sideMenu/SideMenu";

export const Home: FC<any> = ({}) => {
    return(
        <Container className="pt-5">
            <Row>
                <Col>
                    {/*<Row>*/}
                        <QuadrantsGraph/>
                    {/*</Row>*/}
                </Col>
                <Col className="d-none d-md-block">
                    <ShooterForm/>
                </Col>
            </Row>
            <Row className="d-sm-block d-md-none">
                <Col>
                    <ShooterForm/>
                </Col>
            </Row>
            <Row>
                <Col className="pt-3">
                    <AttemptsController/>
                </Col>
            </Row>
        </Container>
    )
}