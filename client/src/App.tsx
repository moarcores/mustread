import React from 'react';
import { useDispatch, useSelector } from "react-redux";
import Login from "./components/Login";
import { RootState } from "./store";


const App =() => {
    const dispatch = useDispatch();

    const authToken = useSelector((state: RootState) => state.login.token);

    if (!authToken) {
        return (
            <Login />
        )
    }

  return (
    <div className="App">
      <h1>Zdarova karti</h1>
    </div>
  );
}

export default App;
