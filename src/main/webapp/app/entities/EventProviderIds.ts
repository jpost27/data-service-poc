import { Events } from './Events';
export interface EventProviderIds {
    eventId: number;
    fanduelEventId?: number;
    numberfireEventId?: number;
    sportsbookEventId?: number;
    sportradarEventId?: string;
    events?: Events;
}
