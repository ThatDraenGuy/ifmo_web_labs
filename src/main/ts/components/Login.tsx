import React, {FC, useEffect, useState} from "react";
import {useAppDispatch} from "../hooks";
import {useDispatch} from "react-redux";
import {useLoginMutation} from "../services/auth";
import {Form} from "react-bootstrap";
import {setAuth} from "../slices/authSlice";
import {Link} from "react-router-dom";

type LoginData = {
    username: string,
    password: string
}


// export default class Login extends React.Component<any, LoginData> {
//     submitForm = (username: string, password: string) => (e: React.FormEvent<HTMLFormElement>) => {
//         e.preventDefault();
//     }
//     render() {
//         return (
//             <div>
//                 <form onSubmit={this.submitForm(this.state.username, this.state.password)}>
//                     <label>Username:
//                         <input type={"text"} value={this.state.username} required={true}/>
//                     </label>
//                     <label>Password:
//                         <input type={"password"} value={this.state.password} required={true}/>
//                     </label>
//                     <input type={"submit"}/>
//                 </form>
//             </div>
//         )
//     }
// }

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
                <Form.Control type="text" required onChange={onUsernameChanged} value={username}/>
                <Form.Control type="password" required onChange={onPasswordChanged} value={password}/>
                <Form.Control type="submit"/>
            </Form>
            <Link to="/">link</Link>
        </div>
    )
}