import React, {FC, useState} from "react";
import {useAppDispatch} from "../hooks";
import {useDispatch} from "react-redux";
import {useLoginMutation} from "../services/auth";

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

export const Login: FC<any> = (props) => {
    const [loginPost, {}] = useLoginMutation();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

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
            <form onSubmit={submitForm}>
                <label>Username:
                    <input type={"text"} onChange={onUsernameChanged} value={username} required={true}/>
                </label>
                <label>Password:
                    <input type={"password"} onChange={onPasswordChanged} value={password} required={true}/>
                </label>
                <input type={"submit"}/>
            </form>
        </div>
    )
}