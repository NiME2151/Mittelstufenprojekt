import {createTheme} from "@mui/material";

export const theme = createTheme({
  palette: {
    mode: "light",
    primary: {
      main: "#A01D31"
    },
    secondary: {
      main: "#AFBC00"
    }
  },
  components: {
    MuiAppBar: {
      defaultProps: {
        position: "static",
        sx: {}
      }
    }
  }
});

export default theme;
