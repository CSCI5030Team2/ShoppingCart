// import history from "../components/history";
import {
    GET_USERS,
    CREATE_USERS,
    RESET_PASSWORD,
    UPDATE_USERS,
    DELETE_USERS,
    LOGIN
  } from "./types";
  
  import axios from "axios";
  
  export const getUsers = () => dispatch => {
    return axios
      .get("http://localhost:8080/users", {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: GET_USERS,
          payload: res.data.data
        });
        console.log(res.data.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const createUsers = users => dispatch => {
    axios
      .post("http://localhost:8080/user/registration", users)
      .then(res => {
        dispatch({
          type: CREATE_USERS
        });
        alert("User created successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const resetPassword = users => dispatch => {
    axios
      .put("http://localhost:7000/api/users/reset_password", users)
      .then(res => {
        dispatch({
          type: RESET_PASSWORD
        });
        console.log(res.data);
        alert("Password updated Successfully");
      })
      .catch(err => {
        console.log(err);
        alert("Username not found");
      });
  };
  
  export const updateUsers = users => dispatch => {
    axios
      .put(
        "http://localhost:7000/api/users/update/" + users._id,
        {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
        },
        users
      )
      .then(res => {
        dispatch({
          type: UPDATE_USERS
        });
        dispatch(getUsers());
        alert("Updated Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  export const deleteUsers = id => dispatch => {
    axios
      .delete("http://localhost:7000/api/users/delete/" + id, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      })
      .then(res => {
        dispatch({
          type: DELETE_USERS
        });
        dispatch(getUsers());
      })
      .catch(err => {
        console.log(err);
      });
  };
  
 
  
  export const login = (users, history) => dispatch => {
    axios
      .post("http://localhost:8080/login", users)
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


  export const forgotPassword = users => dispatch => {
    axios
      .put("http://localhost:7000/api/users/forgot_password", users)
      
  };  
  

  