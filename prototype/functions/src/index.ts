import {onRequest} from "firebase-functions/v2/https";
import * as logger from "firebase-functions/logger";
import express, {Request, Response, NextFunction} from "express";
import {router} from "./router";

const app = express();

app.use(router);

app.use((err: Error, req: Request, res: Response, next: NextFunction) => {
  logger.error(err.message);
  res.sendStatus(400);
});

export const server = onRequest(app);
