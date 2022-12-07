import {FC} from "react";
import {useAppSelector} from "../../hooks";
import {LogoutButton} from "./LogoutButton";

export interface UserDisplayProps {
    onLogoutClick: () => void
}

export const UserDisplay: FC<UserDisplayProps> = ({onLogoutClick}) => {
    const user = useAppSelector(state => state.auth.user);
    if (!user) return (<></>)
    return (
        <div>
            <h1>Current User:</h1>
            <h2>{user.username}</h2>
            <LogoutButton onClick={onLogoutClick}/>
        </div>
    )
}