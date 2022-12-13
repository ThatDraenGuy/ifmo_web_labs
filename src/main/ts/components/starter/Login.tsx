import React, {FC, useEffect, useState} from "react";
import {useAppDispatch} from "../../hooks";
import {useLoginMutation} from "../../services/auth";
import {Alert, Button, Col, Form, Row} from "react-bootstrap";
import {setAuth} from "../../slices/authSlice";
import {StarterTabProps} from "./Starter";


export const Login: FC<StarterTabProps> = ({alert}) => {
    const [loginPost, {data, isSuccess, isError}] = useLoginMutation();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useAppDispatch();
    useEffect(() => {
        if (isSuccess) {
            dispatch(setAuth(data))
        }
    }, [isSuccess])

    useEffect(() => {
        if (isError) alert('danger', 'Incorrect username/password');
    }, [isError])

    const submitForm = (e: React.FormEvent<HTMLFormElement>) =>  {
        e.preventDefault();
        if (e.currentTarget.checkValidity()) loginPost({username, password});
    }

    const onUsernameChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
        alert('primary', '')
    }
    const onPasswordChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
        alert('primary', '')
    }
    return (
        <div>
            <Form noValidate onSubmit={submitForm}>
                <Form.Group className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Enter username" required onChange={onUsernameChanged} value={username}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Enter password" required onChange={onPasswordChanged} value={password}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" disabled={username=='' || password==''}>Log in</Button>
                </Form.Group>
            </Form>
        </div>
    )
}