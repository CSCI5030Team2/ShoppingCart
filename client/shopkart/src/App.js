import React from "react";
import "./App.css";
import Navigation from "./components/Navigation";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProducts from "./components/CreateProduct";
import Register from "./components/register"

function App() {
  return (
    <Router>
      <div>
        <Route exact path="/" component={Navigation}></Route>
        <Route exact path="/createproducts" component={CreateProducts}></Route>
        <Route exact path="/register" component={Register}></Route>
      </div>
    </Router>
  );
}

export default App;
