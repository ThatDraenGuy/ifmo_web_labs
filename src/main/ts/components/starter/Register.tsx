import React, {FC, useState} from "react";
import {Button, Form} from "react-bootstrap";
import {useRegisterMutation, useUsernameExistsQuery} from "../../services/auth";
import {PASSWORD_REGEX} from "../../constants/constants";


export const Register: FC<any> = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [validated, setValidated] = useState(false);
    const [registerPost, {error, isError}] = useRegisterMutation();
    const {data, isLoading} = useUsernameExistsQuery(username);

    const onUsernameChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value)
    }

    const validateUsername = () => {

        return username.length >= 4
    }

    const validatePassword = () => {
        return PASSWORD_REGEX.test(password);
    }

    const onPasswordChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
    }

    const submitForm = (e: React.FormEvent<HTMLFormElement>) =>  {
        e.preventDefault();
        if (validateUsername() && validatePassword()) {
            registerPost({username, password});
            setValidated(true);
        }
        setValidated(false);
    }

    return (
        <Form onSubmit={submitForm} noValidate validated={validated}>
            <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Enter username" required onChange={onUsernameChanged} isValid={validateUsername()} isInvalid={username!='' && ! validateUsername()} value={username}/>
                <Form.Control.Feedback type="invalid">
                    Username should be at least 4 symbols long
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Enter password" required onChange={onPasswordChanged} isValid={validatePassword()} isInvalid={password!='' && ! validatePassword()} value={password}/>
                <Form.Control.Feedback type="invalid">
                    Password should be at least 8 characters long and contain at least 1 letter and 1 number
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3">
                <Button type="submit">Register</Button>
            </Form.Group>
        </Form>
    )
}