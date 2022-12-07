import {FC} from "react";
import {Button} from "react-bootstrap";
import {useLogoutMutation} from "../../services/auth";
import {useAppSelector} from "../../hooks";

export interface LogoutButtonProps {
    onClick: () => void
}

export const LogoutButton: FC<LogoutButtonProps> = ({onClick}) => {
    const [logoutPost, {}] = useLogoutMutation();
    const userId = useAppSelector(state => state.auth.user.id)
    return (
        <Button onClick={
            () => {
                logoutPost(userId);
                onClick()
            }
        }>Logout</Button>
    )
}