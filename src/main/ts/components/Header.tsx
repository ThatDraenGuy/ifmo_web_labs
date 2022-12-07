import React, {FC, useState} from "react";
import {Button, Col, Container, Row} from "react-bootstrap";
import {AUTHOR_LINK, SOURCE_LINK} from "../constants/constants";
import {SideMenu} from "./sideMenu/SideMenu";
import {useAppSelector} from "../hooks";

export const Header: FC<any> = () => {
    const [isShown, setIsShown] = useState(false);
    const user = useAppSelector(state => state.auth.user)

    return (
        <>
            <SideMenu isShown={isShown} setIsShown={setIsShown}/>
            <Container fluid className="bg-primary text-white sticky-top">
                <Row>
                    <Col xs={1} className="position-relative">
                        {user==undefined ? '' :
                            <Button onClick={() => setIsShown(true)} variant="light" className="position-absolute top-50 start-50 translate-middle">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     className="bi bi-list" viewBox="0 0 16 16">
                                    <path fillRule="evenodd"
                                          d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                                </svg>
                            </Button>
                        }
                    </Col>
                    <Col xs={2} className="text-center">Хайкин <a className="link-light d-none d-md-block" href={AUTHOR_LINK}>"@ThatDraenGuy"</a> Олег</Col>
                    <Col xs={2} className="text-center">group P32312</Col>
                    <Col xs={2} className="text-center"><h3>WEB LAB #4</h3></Col>
                    <Col xs={2} className="text-center">var. 66651</Col>
                    <Col xs={2} className="text-center"><a className="link-light" href={SOURCE_LINK}>Source</a></Col>
                    <Col xs={1}/>
                </Row>
            </Container>
        </>
    )
}