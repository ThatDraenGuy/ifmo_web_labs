import {FC} from "react";
import {Container, Tab, Tabs} from "react-bootstrap";
import {Login} from "./Login";
import {Register} from "./Register";


export const Starter: FC<any> = () => {

    return (
        <Container>
            <Tabs defaultActiveKey="login">
                <Tab eventKey="login" title="Login">
                    <Login/>
                </Tab>
                <Tab eventKey="register" title="Register">
                    <Register/>
                </Tab>
            </Tabs>
        </Container>
    )
}