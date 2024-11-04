import { Events } from './Events';
export interface EventProviderIds {
    eventId: number;
    fanduelEventId?: number;
    numberfireEventId?: number;
    sportradarEventId?: string;
    sportsbookEventId?: number;
    events?: Events;
}
