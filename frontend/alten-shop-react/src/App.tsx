import React from 'react';
import {store} from "@/core/store/store";
import {Provider} from "react-redux";
import {BrowserRouter} from 'react-router-dom';
import {AppContainer} from "@/containers/AppContainer";

function App() {


    return (
        <Provider store={store}>
            <BrowserRouter>
                <AppContainer/>
            </BrowserRouter>
        </Provider>
    );
}

export default App;