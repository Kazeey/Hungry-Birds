// lib/config/routes.ts
import {Request, Response} from "express";
import {NodesController} from "../controllers/nodes.controller";
import {LinksController} from "../controllers/links.controller";

export class Routes {
  public nodesController: NodesController = new NodesController();
  public linksController: LinksController = new LinksController();

  public routes(app): void {
    // ...
    app
      .route("/links")
      .get(this.linksController.index)
      .post(this.linksController.create);

    app
      .route("/links/:id")
      .get(this.linksController.show)
      .put(this.linksController.update)
      .delete(this.linksController.delete);
  }
}