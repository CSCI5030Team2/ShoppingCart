import React from "react";
import "./App.css";
import Navigation from "./components/Navigation";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProducts from "./components/CreateProduct";
import Register from "./components/register"
import Login from "./components/Login"
import Cart from "./components/Cart";
import NavigationAfterLogin from "./components/NavigationAfterLogin" ;
import AdminLogin from "./components/AdminLogin";
import AdminNavigation from "./components/AdminNavigation";
import AdminNavigationAfterLogin from "./components/AdminNavigationAfterLogin";
import ResetPassword from "./components/ResetPassword";
import UpdateProduct from "./components/UpdateProduct";
//
function App() {
  return (
    <Router>
      <div>
        <Route exact path="/" component={Navigation}></Route>
        <Route exact path="/createproducts" component={CreateProducts}></Route>
        <Route exact path="/register" component={Register}></Route>
        <Route exact path="/login" component={Login}></Route>
        <Route exact path="/cart" component={Cart}></Route>
        <Route exact path="/navigation" component={NavigationAfterLogin}></Route>
        <Route exact path="/reset" component={ResetPassword}></Route>
        <Route exact path="/AdminLogin" component={AdminLogin}></Route>
        <Route exact path="/AdminNavigation" component={AdminNavigation}></Route>
        <Route exact path="/AdminNavigationAfterLogin" component={AdminNavigationAfterLogin}></Route>
        <Route exact path="/UpdateProduct/:itemName" component={UpdateProduct}></Route>
      </div>
    </Router>
  );
}

export default App;
