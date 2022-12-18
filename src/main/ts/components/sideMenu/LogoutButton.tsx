import {FC} from "react";
import {Button} from "react-bootstrap";
import {useGetCurrentUserQuery, useLogoutMutation} from "../../services/auth";
import {useAppSelector} from "../../hooks";

export interface LogoutButtonProps {
    onClick: () => void
}

export const LogoutButton: FC<LogoutButtonProps> = ({onClick}) => {
    const [logoutPost, {}] = useLogoutMutation();
    const {currentData: user} = useGetCurrentUserQuery();
    return (
        <Button onClick={
            () => {
                logoutPost(user.id);
                onClick()
            }
        }>Logout</Button>
    )
}