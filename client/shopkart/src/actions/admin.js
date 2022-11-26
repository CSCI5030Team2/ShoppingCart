// import history from "../components/history";
import {
    GET_ADMIN,
    LOGIN
  } from "./types";
  
  import axios from "axios";
  
  export const getAdmin = () => dispatch => {
    return axios
      .get("http://localhost:8080/admin", {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: GET_ADMIN,
          payload: res.data.data
        });
        console.log(res.data.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  
  export const loginAdmin = (admin, history) => dispatch => {
    axios
      .post("http://localhost:8080/admin/login", admin)
      .then(res => {
        //console.log(res.data[0]);

        //Save the login token of that user to cache
        localStorage.setItem("token", res.data[0]);

        //Save the role of that user to cache
        localStorage.setItem("role", res.data[1]);
        dispatch({
          type: LOGIN
        });
        //console.log(res.data);
        alert("Login successful");
        // history.push("/displayusers");
      })
      .catch(err => {
        console.log(err);
        alert("Invalid Credentials");
      });
  };
  

  