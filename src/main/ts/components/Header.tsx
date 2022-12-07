import React, {FC} from "react";
import {Col, Container, Row} from "react-bootstrap";
import {AUTHOR_LINK, SOURCE_LINK} from "../constants/constants";

export const Header: FC<any> = () => {

    // return (
    //     <div className="d-flex justify-content-around bg-primary text-white">
    //         <div className="text-center">Хайкин Олег</div>
    //         <div className="text-center">group P32312</div>
    //         <div className="text-center"><h1>WEB LAB #4</h1></div>
    //         <div className="text-center">var. 66651</div>
    //         <div className="text-center">Source</div>
    //     </div>
    // )

    return (
        <Container fluid className="bg-primary text-white sticky-top">
            <Row className="pt-3">
                <Col className="text-center">Хайкин "<a className="link-light" href={AUTHOR_LINK}>@ThatDraenGuy</a>" Олег</Col>
                <Col className="text-center">group P32312</Col>
                <Col className="text-center"><h1>WEB LAB #4</h1></Col>
                <Col className="text-center">var. 66651</Col>
                <Col className="text-center"><a className="link-light" href={SOURCE_LINK}>Source</a></Col>
            </Row>
        </Container>
    )
}