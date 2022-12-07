import {FC} from "react";
import {Col, Container, Row, Tab, Tabs} from "react-bootstrap";
import {Login} from "./Login";
import {Register} from "./Register";
import * as styles from "../misc/center.module.css";

export const Starter: FC<any> = () => {

    return (
        <Container >
            <Row className={styles.verticalHolder}>
                <Col/>
                <Col className={styles.verticalCenter} sm={8} md={8} lg={6}>
                    <div  className="shadow p-3 mb-5 bg-body rounded">
                        <Tabs defaultActiveKey="login">
                            <Tab eventKey="login" title="Login">
                                <div className="pt-1">
                                    <Login/>
                                </div>
                            </Tab>
                            <Tab eventKey="register" title="Register">
                                <div className="pt-1">
                                    <Register/>
                                </div>
                            </Tab>
                        </Tabs>
                    </div>
                </Col>
                <Col/>
            </Row>
        </Container>
    )
}