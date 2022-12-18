import {FC} from "react";
import {useAppSelector} from "../../hooks";
import {LogoutButton} from "./LogoutButton";
import {useGetCurrentUserQuery} from "../../services/auth";

export interface UserDisplayProps {
    onLogoutClick: () => void
}

export const UserDisplay: FC<UserDisplayProps> = ({onLogoutClick}) => {
    const {currentData: user} = useGetCurrentUserQuery();
    if (!user) return (<></>)
    return (
        <div>
            <h1>Current User:</h1>
            <h2>{user.username}</h2>
            <LogoutButton onClick={onLogoutClick}/>
        </div>
    )
}