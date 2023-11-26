/* eslint-disable */

import { Router } from "express";
import {sendMailHandler} from "../handler/mail";
import {User} from "../model/user";
import {validate} from "class-validator";
import {createUserHandler, getUserHandler} from "../handler/user";

const router = Router();

router.post("email", async (req, res) => {
  const email: string = req.body.email;

  await sendMailHandler({
    to: [email],
    message: {
      subject: "test",
      text: "0000",
    },
  });

  res.status(200).send({
    certification_key: "test-key",
  });
});

router.post("email/check", (req, res) => {
  const key: string = req.body.certification_key;
  const code: string = req.body.certification_code;

  res.status(200).send({
    result: key === "test-key" ? (code === "0000" ? 0 : 1) : 2,
  });
});

router.post("/signup", async (req, res) => {
  const user = new User(
    req.body.email,
    req.body.password,
    req.body.name,
    req.body.tel,
    req.body.birth
  );

  const errs = await validate(user);

  if (errs.length) {
    throw new Error();
  }

  await createUserHandler(user);

  res.sendStatus(200);
});

router.post("auth/login", async (req, res) => {
  const email: string = req.body.email;
  const password: string = req.body.password;

  const user = await getUserHandler(email);

  if (password !== user.password) {
    throw new Error();
  }

  res.status(200).send({
    access_token: "test-access-token",
    refresh_token: "test-refresh-token",
  });
});

router.get("auth/logout", (req, res) => {
  res.sendStatus(200);
});

export {router};
