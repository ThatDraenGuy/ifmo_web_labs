import {FC, useState} from "react";
import {Alert, Col, Container, Row, Tab, Tabs} from "react-bootstrap";
import {Login} from "./Login";
import {Register} from "./Register";
import * as styles from "../misc/center.module.css";

export interface StarterTabProps {
    alert: (type: string, text: string) => void;
}

export const Starter: FC<any> = () => {
    const [alertType, setAlertType] = useState('primary');
    const [alertText, setAlertText] = useState('');

    const alert = (type: string, text: string) => {
        setAlertType(type);
        setAlertText(text);
    }

    return (
        <Container >
            <Row className={styles.verticalHolder}>
                <Col/>
                <Col className={styles.verticalCenter} sm={10} md={8} lg={6}>
                    <div  className="shadow p-3 mb-5 bg-body rounded">
                        <Tabs defaultActiveKey="login" onSelect={() => alert('primary', '')}>
                            <Tab eventKey="login" title="Login">
                                <div className="pt-1">
                                    <Login alert={alert}/>
                                </div>
                            </Tab>
                            <Tab eventKey="register" title="Register">
                                <div className="pt-1">
                                    <Register alert={alert}/>
                                </div>
                            </Tab>
                        </Tabs>
                        {alertText=='' ? '' : <Alert variant={alertType}>{alertText}</Alert> }
                    </div>
                </Col>
                <Col/>
            </Row>
        </Container>
    )
}