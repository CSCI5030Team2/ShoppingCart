import React from "react";
import "./App.css";
import Navigation from "./components/Navigation";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProducts from "./components/CreateProduct";
import Register from "./components/register"
import Login from "./components/Login"
import Cart from "./components/Cart";
<<<<<<< HEAD
import Checkout from "./components/Checkout";
=======
import Checkout from "./components/Checkout"
>>>>>>> vivek
import NavigationAfterLogin from "./components/NavigationAfterLogin" ;
import ResetPassword from "./components/ResetPassword";
<<<<<<< HEAD

=======
import Forgotpassword from "./components/Forgotpassword";
//
>>>>>>> origin/bohan
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
<<<<<<< HEAD
        <Route exact path="/checkout" component={Checkout}></Route>
=======
        <Route exact path="/forgot" component={Forgotpassword}></Route>
>>>>>>> origin/bohan
      </div>
    </Router>
  );
}

export default App;
