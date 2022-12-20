import React, {FC, useEffect, useState} from "react";
import {Alert, Button, Form, Spinner} from "react-bootstrap";
import {useRegisterMutation, useUsernameExistsQuery} from "../../services/auth";
import {PASSWORD_REGEX} from "../../constants/constants";
import {StarterTabProps} from "./Starter";
import {PasswordInput} from "./PasswordInput";
import {LoginWithPolling} from "./LoginWithPollingInput";



export const Register: FC<StarterTabProps> = ({alert}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [validated, setValidated] = useState(false);
    const [shouldCheckUsername, setShouldCheckUsername] = useState(false);
    const [registerPost, {error, isError, isSuccess}] = useRegisterMutation();
    const {data: isUsernameTaken, isLoading: isUsernameChecking, isSuccess: isUsernameChecked} = useUsernameExistsQuery(username, {skip: !shouldCheckUsername});

    useEffect(() => {
        if (isSuccess) alert("success", "Successfully registered new user");
        if (isError) alert("warning", "Unknown error occurred");
    }, [isSuccess, isError])

    const onUsernameChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value)
        alert('primary', '')
    }

    const isUsernameValid = (username: string) => {
        return username.length >= 4
    }

    const validateUsername = () => {
        if (!isUsernameChecking) return !isUsernameTaken && isUsernameValid(username)
        return isUsernameValid(username);
    }

    const validatePassword = () => {
        return PASSWORD_REGEX.test(password);
    }

    const onPasswordChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value)
        alert('primary', '')
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
                {/*<Form.Control type="text" placeholder="Enter username" required onChange={onUsernameChanged} isValid={validateUsername()} isInvalid={username!='' && ! validateUsername()} value={username}/>*/}
                {/*<Form.Control.Feedback type="invalid">*/}
                {/*    {isUsernameTaken ? 'This username is taken!' : 'Username should be at least 4 symbols long'}*/}
                {/*</Form.Control.Feedback>*/}
                <LoginWithPolling onChanged={onUsernameChanged} isValid={validateUsername()} isInvalid={username!='' && ! validateUsername()} value={username} validateUsername={isUsernameValid}
                                  initiateCheck={setShouldCheckUsername} isChecked={isUsernameChecked} feedback={
                    <Form.Control.Feedback type="invalid">
                        {isUsernameTaken ? 'This username is taken!' : 'Username should be at least 4 symbols long'}
                    </Form.Control.Feedback>
                }/>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                {/*<Form.Control type="password" placeholder="Enter password" required onChange={onPasswordChanged} isValid={validatePassword()} isInvalid={password!='' && ! validatePassword()} value={password}/>*/}
                <PasswordInput onChanged={onPasswordChanged} isValid={validatePassword()} isInvalid={password!='' && ! validatePassword()} value={password}
                feedback={
                    <Form.Control.Feedback type="invalid">
                        Password should be at least 8 characters long and contain at least 1 letter and 1 number
                    </Form.Control.Feedback>
                }/>
                {/*<Form.Control.Feedback type="invalid">*/}
                {/*    Password should be at least 8 characters long and contain at least 1 letter and 1 number*/}
                {/*</Form.Control.Feedback>*/}
            </Form.Group>
            <Form.Group className="mb-3">
                <Button type="submit" disabled={!validateUsername() || !validatePassword() || !isUsernameChecked}>Register</Button>
            </Form.Group>
        </Form>
    )
}