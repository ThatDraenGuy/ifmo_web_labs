import React, {FC, useEffect, useState} from "react";
import {useAppDispatch} from "../../hooks";
import {useDispatch} from "react-redux";
import {useLoginMutation} from "../../services/auth";
import {Button, Col, Form, Row} from "react-bootstrap";
import {setAuth} from "../../slices/authSlice";
import {Link} from "react-router-dom";
import {ChooserWrapper} from "../chooser/ChooserWrapper";

type LoginData = {
    username: string,
    password: string
}


export const Login: FC<any> = () => {
    const [loginPost, {data, isSuccess}] = useLoginMutation();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useAppDispatch();

    useEffect(() => {
        if (isSuccess) {
            dispatch(setAuth(data))
        }
    }, [isSuccess])

    const submitForm = (e: React.FormEvent<HTMLFormElement>) =>  {
        e.preventDefault();
        loginPost({username, password});
    }

    const onUsernameChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value)
    }
    const onPasswordChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
    }
    return (
        <div>
            <Form onSubmit={submitForm}>
                <Form.Group className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Enter username" required onChange={onUsernameChanged} value={username}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Enter password" required onChange={onPasswordChanged} value={password}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit">Log in</Button>
                </Form.Group>
            </Form>
        </div>
    )
}