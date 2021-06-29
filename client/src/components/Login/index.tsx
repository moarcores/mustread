import { useDispatch } from "react-redux";
import GoogleLogin from 'react-google-login';

const googleOauth = async () => {
    console.log('log')
    const token = await fetch('')
}

const Login = () => {
    const dispatch = useDispatch();

    return (
        <GoogleLogin
            clientId="319809391275-uo5ki4mbla9rful4led6kp9fjtlfqbf7.apps.googleusercontent.com"
            buttonText="LOGIN WITH GOOGLE"
            onSuccess={googleOauth}
            onFailure={googleOauth}
        />

    )
}

export default Login;