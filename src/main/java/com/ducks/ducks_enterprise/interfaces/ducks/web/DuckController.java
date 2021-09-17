package com.ducks.ducks_enterprise.interfaces.ducks.web;

import com.ducks.ducks_enterprise.application.DuckService;
import com.ducks.ducks_enterprise.interfaces.ducks.DuckDTO;
import com.ducks.ducks_enterprise.interfaces.payload.response.ClientMessageResponse;
import com.ducks.ducks_enterprise.interfaces.util.EndPoints;
import com.ducks.ducks_enterprise.interfaces.util.Messages;
import com.ducks.ducks_enterprise.interfaces.util.PathVariables;
import com.ducks.ducks_enterprise.interfaces.validation.ResponseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.END_POINT_DUCK)
@RequiredArgsConstructor
public class DuckController {

    private final DuckService duckService;
    private final ResponseValidator responseValidator;

    @GetMapping(EndPoints.END_POINT_DUCK_GET_BY_ID)
    public ResponseEntity<Object> getDuckById(@PathVariable(PathVariables.DUCK_ID) String duckId) {
        try {
            var response = duckService.getDuckById(Long.parseLong(duckId));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ClientMessageResponse(Messages.SOMETHING_WENT_WRONG), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(EndPoints.END_POINT_DUCK_GET_ALL)
    public ResponseEntity<Object> getAllDucks() {
        try {
            var response = duckService.getAllDucks();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ClientMessageResponse(Messages.SOMETHING_WENT_WRONG), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(EndPoints.END_POINT_DUCK_ADD)
    public ResponseEntity<Object> addNewDuck(@RequestBody DuckDTO dto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseValidator.validate(bindingResult);
        if (errors != null) return errors;

        try {
            var response = duckService.addNewDuck(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ClientMessageResponse(Messages.SOMETHING_WENT_WRONG), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(EndPoints.END_POINT_DUCK_UPDATE)
    public ResponseEntity<Object> updateDuck(@RequestBody DuckDTO dto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseValidator.validate(bindingResult);
        if (errors != null) return errors;

        try {
            var response = duckService.updateDuck(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ClientMessageResponse(Messages.SOMETHING_WENT_WRONG), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(EndPoints.END_POINT_DUCK_REMOVE)
    public ResponseEntity<Object> removeDuck(@RequestBody DuckDTO dto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseValidator.validate(bindingResult);
        if (errors != null) return errors;

        var response = new ClientMessageResponse();
        try {
            duckService.removeDuck(dto);
            response.setMessage(Messages.OPERATION_SUCCESSFULLY);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(Messages.SOMETHING_WENT_WRONG);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
