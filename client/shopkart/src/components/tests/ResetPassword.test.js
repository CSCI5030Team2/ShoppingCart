import React from "react";
import { mount } from "enzyme";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";
import Resetpassword from "../ResetPassword";

const ResetPassword = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <ResetPassword ResetPassword={Resetpassword} />
    </BrowserRouter>
  </Provider>
);

describe("Test ResetPassword Component", () => {
    it("should render the component", () => {
      expect(wrapper).toMatchSnapshot();
    });
  
    it("should have exactly five input fields", () => {
      expect(wrapper.find("input").length).toBe(5);
    });
  
    it("should have one form component", () => {
      expect(wrapper.find("form").length).toBe(1);
    });
  
    it("should have exactly one button", () => {
      expect(wrapper.find("button").length).toBe(1);
    });

    it("should have Enter Email ID placeholder on Email input field", () => {
        expect(
          wrapper
            .find("input")
            .at(0)
            .props().placeholder
        ).toBe("Enter Email ");
    });
    
    it("should have Enter New Password placeholder on password input field", () => {
      expect(
          wrapper
            .find("input")
            .at(1)
            .props().placeholder
        ).toBe("Enter New Password");
    });

    it("should have Re-Enter New Password placeholder on password input field", () => {
        expect(
          wrapper
            .find("input")
            .at(2)
            .props().placeholder
        ).toBe("Re-Enter New Password");
    });

    
 });
      
    
    
  