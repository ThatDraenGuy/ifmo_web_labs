import {FC} from "react";
import {useAppSelector} from "../../hooks";
import {LogoutButton} from "./LogoutButton";
import {Header} from "../Header";


export const UserDisplay: FC<any> = () => {
    const user = useAppSelector(state => state.auth.user);
    return (
        <div>
            <h1>Current User:</h1>
            <h2>{user.username}</h2>
            <LogoutButton/>
        </div>
    )
}