export interface Message {
    readonly subject: string;
    readonly text: string;
}

export interface Mail {
    readonly to: Array<string>;
    readonly message: Message;
}

